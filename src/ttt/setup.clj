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
      (if (contains? #{"1" "2"} size)
      size
      (do (invalid-board-size)
        (recur (next sizes)))))))

(defn players []
  (loop [players-array []]
    (if (= 2 (count players-array))
      players-array
      (recur (conj players-array (player/create-player))))))




