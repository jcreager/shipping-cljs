(ns shipping-cljs.core
  (:require
    [kee-frame.core :as kf]
    [re-frame.core :as rf]
    [ajax.core :as http]
    [shipping-cljs.ajax :as ajax]
    [shipping-cljs.routing :as routing]
    [shipping-cljs.view :as view]))


(rf/reg-event-fx
  ::load-about-page
  (constantly nil))

(kf/reg-controller
  ::about-controller
  {:params (constantly true)
   :start  [::load-about-page]})

(rf/reg-sub
  :docs
  (fn [db _]
    (:docs db)))

(rf/reg-event-db
 :install-prompt
 (fn [db [_ prompt]]
   (assoc db :install-prompt prompt)))

(rf/reg-sub
 :install-prompt
 (fn [db _]
   (:install-prompt db)))

(kf/reg-chain
  ::load-home-page
  (fn [_ _]
    {:http-xhrio {:method          :get
                  :uri             "/docs"
                  :response-format (http/raw-response-format)
                  :on-failure      [:common/set-error]}})
  (fn [{:keys [db]} [_ docs]]
    {:db (assoc db :docs docs)}))

(kf/reg-controller
  ::home-controller
  {:params (constantly true)
   :start  [::load-home-page]})

;; -------------------------
;; Initialize app
(defn mount-components
  ([] (mount-components true))
  ([debug?]
    (rf/clear-subscription-cache!)
    (kf/start! {:debug?         (boolean debug?)
                :routes         routing/routes
                :hash-routing?  true
                :initial-db     {}
                :root-component [view/root-component]})))

(defn install-prompt-listener []
  (js/window.addEventListener
   "beforeinstallprompt"
   (fn [e]
     (do (js/console.log "hi")
         (.preventDefault e)
         (rf/dispatch [:install-prompt e])))))

(defn init! [debug?]
  (install-prompt-listener)
  (ajax/load-interceptors!)
  (mount-components debug?))
