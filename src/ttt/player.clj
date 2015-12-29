(ns ttt.player
  (:require [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]))

(defn- get-player-name []
  (output/prompt views/player-name)
  (input/get-user-input))

(def player {:name (get-player-name)})

