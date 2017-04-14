(ns buxtonarchitecture.basic
  (:require [cljs.spec :as s]))

;; Spec Definitions
(def url-pattern #"(?i)^(?:(?:https?|ftp)://)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,}))\.?)(?::\d{2,5})?(?:[/?#]\S*)?$")

(s/def ::empty #(= :none %))
(s/def ::text string?)
(s/def ::url #(re-matches url-pattern %))
(s/def ::url string?)

(s/def ::link (s/cat :text ::text
                     :url ::url))

(s/fdef hyperlink
        :args (s/cat :link ::link)
        :ret vector?)

;; Data Definitions
(defn br
  "Inserts a number of breaks.
  @param number The number of times you want to insert a line break."
  ([] (br 1))
  ([number]
   (for [n (range number)]
     [:br {:key n}])))

(defn button
  "A stylized button."
  [btn]
  (if (= (:callback btn) :none)
    [:button.btn (:text btn)]
    [:button.btn {:on-click (:callback btn)} (:text btn)]))

(defn hyperlink
  "A stylized anchor tag.
  @param "
  [link]
  [:a.link {:href (if (= (:url link) :none)
                    "#"
                    (:url link))
            :key (random-uuid)}
           (:text link)])

(defn horizontal-list
  "A horizontal list of hyperlinks."
  [hl]
  [:ul.horizontal-list
   (map hyperlink hl)])

(s/fdef plus
        :args (s/cat :one even?)
        :ret #(= :one %))

(defn plus
  [one]
  {:pre [(even? one)]}
  one)

