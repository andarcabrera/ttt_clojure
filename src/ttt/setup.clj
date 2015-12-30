(ns ttt.setup
  (:require [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]
            [ttt.player :as player]))

(defn- get-board-size []
  (output/prompt views/board-size)
  (input/get-user-input))

(defn- invalid-board-size []
  (output/prompt views/invalid-board-size))


(defn board-size []
  (loop [sizes (repeatedly get-board-size)]
    (let [size (first sizes)]
      (cond
        (= "1" size) 9
        (= "2" size) 16
        :else
      (do (invalid-board-size)
        (recur (next sizes)))))))

(defn- players []
  (loop [players-array []]
    (if (= 2 (count players-array))
      players-array
      (recur (conj players-array (player/create-player))))))

(defn validate-players []
  (loop [players (players)]
    (let [player1 (first players) player2 (last players)]
    (if (not= (player1 :marker) (player2 :marker))
      players
      (do (output/prompt views/duplicate-marker)
      (recur [player1 (assoc player2 :marker (player/validate-input player/get-player-marker))]))))))







