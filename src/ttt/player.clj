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

(defn- validate-input [input]
  (loop [selections (repeatedly input)]
    (let [selection (first selections)]
      (if (not= "" selection)
        selection
      (do (output/prompt views/no-input)
        (recur (next selections)))))))

(defn- spot-selection []
  (output/prompt views/spot-selection)
  (input/get-user-input))

(defn- invalid-spot []
  (output/prompt views/invalid-spot))

(defn create-player []
  {:name (validate-input get-player-name)
   :marker (validate-input get-player-marker)})

(defn select-spot [board]
  (loop [selections (repeatedly spot-selection)]
    (let [selection (Integer/parseInt (first selections))]
      (if (board/available-spot? board selection)
        selection
      (do (invalid-spot)
        (recur (next selections)))))))



