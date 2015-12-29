(ns ttt.player
  (:require [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]
            [ttt.board :as board]))

(defn- get-player-name []
  (output/prompt views/player-name)
  (input/get-user-input))

(defn- get-player-marker []
  (output/prompt views/player-marker)
  (input/get-user-input))

(defn- spot-selection []
  (output/prompt views/spot-selection)
  (input/get-user-input))

(defn- invalid-spot []
  (output/prompt views/invalid-spot))

(defn create-player []
  {:name (get-player-name)
   :marker (get-player-marker)})

(defn select-spot [board]
  (loop [selections (repeatedly spot-selection)]
    (let [selection (Integer/parseInt (first selections))]
      (if (board/available-spot? board selection)
        selection
      (do (invalid-spot)
        (recur (next selections)))))))



