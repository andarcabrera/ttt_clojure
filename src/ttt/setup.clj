(ns ttt.setup
  (:require [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]
            [ttt.player :as player]))

(defn- get-board-size []
  (output/prompt views/board-size)
  (input/get-user-input))

(defn- get-game-type []
  (output/prompt views/game-type)
  (input/get-user-input))

(defn- get-players-order [original-players]
  (output/prompt (str views/starting-player (clojure.string/capitalize ((first original-players) :name))))
  (input/get-user-input))

(defn- invalid-board-size []
  (output/prompt views/invalid-board-size))

(defn- invalid-game-type []
  (output/prompt views/invalid-game-type))

(defn- invalid-player-order []
  (output/prompt views/invalid-starting-player))

(defn board-size []
  (loop [sizes (repeatedly get-board-size)]
    (let [size (first sizes)]
      (cond
        (= "1" size) 9
        (= "2" size) 16
        :else
      (do (invalid-board-size)
        (recur (next sizes)))))))

(defn game-type []
  (loop [types (repeatedly get-game-type)]
    (let [type (first types)]
      (cond
        (= "1" type) 1
        (= "2" type) 2
        (= "3" type) 3
        :else
      (do (invalid-game-type)
        (recur (next types)))))))

(defn players-order [validated-players]
  (loop [players validated-players]
    (let [input (get-players-order players)]
      (cond
        (or (= "y" input) (= "Y" input)) "Y"
        (or (= "n" input) (= "N" input)) "N"
        :else
      (do (invalid-player-order)
        (recur players))))))

(defn- create-human-players []
  [(player/create-human-player) (player/create-human-player)])

(defn- create-human-computer-players []
  [(player/create-human-player) (player/create-computer-player)])

(defn- create-computer-players []
  [(player/create-computer-player) (player/create-computer-player)])

(defn- players []
  (let [game-type (game-type)]
    (cond
      (= 1 game-type)
        (create-human-players)
      (= 2 game-type)
        (create-human-computer-players)
      (= 3 game-type)
        (create-computer-players))))

(defn- validate-players []
  (loop [players (players)]
    (let [player1 (first players) player2 (last players)]
    (if (not= (player1 :marker) (player2 :marker))
      players
      (do (output/prompt views/duplicate-marker)
      (recur [player1 (assoc player2 :marker (player/validate-input player/get-player-marker))]))))))

(defn ordered-players []
  (let [current-players (validate-players) players-order (players-order current-players)]
    (cond
      (= "Y" players-order)
        current-players
      (= "N" players-order)
        (reverse current-players))))







