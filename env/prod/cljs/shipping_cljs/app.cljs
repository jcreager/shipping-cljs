(ns shipping-cljs.app
  (:require [shipping-cljs.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init! false)
