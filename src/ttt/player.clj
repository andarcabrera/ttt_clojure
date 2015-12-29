(ns ttt.player
  (:require [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]))

(defn- get-player-name []
  (output/prompt views/player-name)
  (input/get-user-input))

(defn- get-player-marker []
  (output/prompt views/player-marker)
  (input/get-user-input))

(defn player []
  {:name (get-player-name)
   :marker (get-player-marker)})

