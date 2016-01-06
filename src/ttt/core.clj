(ns ttt.core
  (:require [ttt.board :as board]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]
            [ttt.setup :as setup]
            [ttt.player :as player]
            ))


(defn- spot [board markers player-marker type]
  (player/select-spot {:board board :markers markers :player-marker player-marker :type type}))

(defn- player-message [message player]
  (str message (clojure.string/capitalize (player :name))))

(defmulti prompt-message :type)

(defmethod prompt-message "human" [player]
  (output/prompt (player-message views/spot-selection player)))

(defmethod prompt-message "computer" [player]
  (output/prompt views/computer-move))

(defn- play-game [board original-players]
  (loop [board board players original-players original-markers (map #(% :marker) original-players)]
    (let [player (first players) marker (player :marker) type (player :type)]
    (cond
      (board/solved-board? board)
        (do (board/display-board board)
            (output/prompt (player-message views/winning-message (second players))))
      (board/tied-board? board)
        (do (board/display-board board)
            (output/prompt views/tie-message))
      :else
        (do (board/display-board board)
            (prompt-message player)
            (recur (board/fill-spot board (spot board original-markers marker type) marker) (reverse players) original-markers))))))

(defn -main []
  (output/prompt views/welcome-message)
  (let [players (setup/ordered-players) board (board/surface (setup/board-size))]
    (play-game board players)))




