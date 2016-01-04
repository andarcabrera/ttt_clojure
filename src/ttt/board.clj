(ns ttt.board
  (:require [ttt.output :as output]))

(defn surface [size]
  (vec (range size)))

(defn- partitioner [board]
  (int (Math/sqrt (count board))))

(defn- partitioned-board [board]
  (partition (partitioner board) board))

(defn- transpose-board [board]
  (apply mapv vector board))

(defn- check-rows [board]
  (map #(= 1 (count (set %))) (partitioned-board board)))

(defn- check-columns [board]
  (map #(= 1 (count (set %))) (transpose-board (partitioned-board board))))

(defn- get-left-diagonal [board]
  (loop [current-position 0 diagonal [] partitioner (partitioner board)]
    (if (= partitioner (count diagonal))
      diagonal
      (if (= 0 (mod current-position (+ 1 partitioner)))
        (recur (inc current-position) (conj diagonal (board current-position)) partitioner)
        (recur (inc current-position) diagonal partitioner)))))

(defn- get-right-diagonal [board]
  (loop [partitioner (partitioner board) current-position (- partitioner 1) diagonal []]
    (if (= partitioner (count diagonal))
      diagonal
    (recur partitioner (+ (- partitioner 1) current-position) (conj diagonal (board current-position))))))

(defn- solved-row? [board]
  (some true? (check-rows board)))

(defn- solved-column? [board]
  (some true? (check-columns board)))

(defn- solved-left-diagonal? [board]
  (if (= 1 (count (set (get-left-diagonal board)))) true false))

(defn- solved-right-diagonal? [board]
  (if (= 1 (count (set (get-right-diagonal board)))) true false))

(defn solved-board? [board]
  (if (or (solved-row? board) (solved-column? board) (solved-left-diagonal? board) (solved-right-diagonal? board)) true false))

(defn available-spot? [board position]
  (= (board position) position))

(defn all-available-spots [board]
  (filter #(available-spot? board %) (range 9)))

(defn fill-spot [board position marker]
  (if (available-spot? board position)
   (assoc board position marker)
   board))

(defn tied-board? [board]
  (and (not-any? true? (map = board (range 9))) (= false (solved-board? board))))

(defn display-board [board]
  (doseq [row (partitioned-board board)]
  (output/prompt row)))

(defn display-board [board]
  (doseq [row (partitioned-board board)]
    (output/prompt row)))





