(ns ttt.core
  (:require [ttt.board :as board]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]))

(defn -main []
  (output/prompt views/welcome-message))




