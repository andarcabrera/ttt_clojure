(ns ttt.core
  (:require [ttt.board :as board]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]
            [ttt.setup :as setup]
            ))

(defn -main []
  (output/prompt views/welcome-message)
  (let [players (setup/players) board (board/surface (setup/board-size))]
    (board/display-board board)
    (println players)))




