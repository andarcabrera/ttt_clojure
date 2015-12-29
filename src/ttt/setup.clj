(ns ttt.setup
  (:require [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]))

(defn- get-board-size []
  (output/prompt views/board-size)
  (input/get-user-input))

(defn invalid-board-size []
  (output/prompt views/invalid-board-size))

(defn board-size []
  (if (contains? {1 2} (get-board-size))
      (get-board-size)
      (invalid-board-size)))

