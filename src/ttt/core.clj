(ns ttt.core
  (:require [ttt.board :as board]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]
            [ttt.setup :as setup]
            [ttt.player :as player]
            ))

(defn- marker [player]
  (player :marker))

(defn- spot [board markers type]
  (player/select-spot {:board board :markers markers :type type}))

(defn- player-message [message player]
  (str message (player :name)))

(defn- play-game [board players]
  (loop [board board players players]
    (let [player (first players) markers (map #(% :marker) players) type (player :type)]
    (cond
      (board/solved-board? board)
        (do (board/display-board board)
            (output/prompt (player-message views/winning-message (second players))))
      (board/tied-board? board)
        (do (board/display-board board)
            (output/prompt views/tie-message))
      :else
      (do (board/display-board board)
          (output/prompt (player-message views/spot-selection player))
          (recur (board/fill-spot board (spot board markers type) (marker player)) (reverse players)))))))

(defn -main []
  (output/prompt views/welcome-message)
  (let [players (setup/ordered-players) board (board/surface (setup/board-size))]
    (play-game board players)))




