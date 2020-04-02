(ns shipping-cljs.routes.home
  (:require
   [shipping-cljs.layout :as layout]
   [clojure.java.io :as io]
   [shipping-cljs.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]
   [page-renderer.api :as pr]))

(defn service-worker [request]
  (pr/respond-service-worker
   {:script "/js/app.js"
    :sw-default-url "/"
    :sw-add-assets
    ["/css/screen.css"
     "/img/warning_clojure.png"
     "/icons/android-chrome-192x192.png"
     "/icons/android-chrome-512x512.png"]}))


(defn home-page [request]
  (layout/render request "home.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "docs/docs.md" io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]
   ["/service-worker.js" {:get service-worker}]])
