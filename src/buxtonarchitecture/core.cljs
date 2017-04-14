(ns buxtonarchitecture.core
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.spec :as s]
            [buxtonarchitecture.state :as state]
            [buxtonarchitecture.basic :as b]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

;(defonce app-state (atom state/data))
(def app-state (atom state/data))

;(defn hello-world []
  ;[:h1 (:text @app-state)])

(defn example []
  [:div
   (b/horizontal-list [{:text "item1"
                        :url "http://www.google.com"}
                       {:text "item2"
                        :url "http://www.google.com"}])
   (b/hyperlink {:text "Goes Nowhere"
                 :url :none})
   (b/br 2)
   (b/hyperlink {:text "google.com"
                 :url "http://www.google.com/"})
   (b/br 2)
   (b/button {:text "does nothing"})
   (b/br 2)
   (b/button {:text "println"
              :callback #(println "Printed!")})
   ])

(reagent/render-component [example]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ; optionally touch your app-state to force rerendering depending on
  ; your application
   (swap! app-state update-in [:__figwheel_counter] inc)
)
