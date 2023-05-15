(ns app.ui
  (:require [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
            [com.fulcrologic.fulcro.dom :as dom]))

(defsc Person [this {:person/keys [name age]}]
  (dom/li
   (dom/h5 (str name " (age: " age ")"))))

(def ui-person (comp/factory Person))

(defsc PersonList [this {:list/keys [label people]}]
  (dom/div
   (dom/h4 label)
   (dom/ul (mapv ui-person people))))

(def ui-person-list (comp/factory PersonList))

(defsc Root [this {:keys [ui/react-key]}]
  (let [ui-data {:friends {:list/label  "Friends"
                           :list/people [{:person/name "Salley" :person/age 32}
                                         {:person/name "Joe" :person/age 22}]}
                 :enemies {:list/label  "Enemies"
                           :list/people [{:person/name "Fred" :person/age 11}
                                         {:person/name "Boddy" :person/age 55}]}}]
    (dom/div
     (ui-person-list (:friends ui-data))
     (ui-person-list (:enemies ui-data)))))
