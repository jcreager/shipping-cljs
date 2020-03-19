(ns shipping-cljs.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [shipping-cljs.core-test]))

(doo-tests 'shipping-cljs.core-test)

