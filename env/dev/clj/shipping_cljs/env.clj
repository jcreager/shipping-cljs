(ns shipping-cljs.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [shipping-cljs.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[shipping-cljs started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[shipping-cljs has shut down successfully]=-"))
   :middleware wrap-dev})
