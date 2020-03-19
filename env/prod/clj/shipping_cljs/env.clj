(ns shipping-cljs.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[shipping-cljs started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[shipping-cljs has shut down successfully]=-"))
   :middleware identity})
