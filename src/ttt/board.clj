(ns ttt.board)

(defn surface [size]
  (vec (range size)))

(defn- partitioned-board [board]
  (partition 3 board))

(defn- transpose-board [board]
  (apply mapv vector board))

(defn- check-rows [board]
  (let [board-rows (transpose-board (partitioned-board board))]
  (map = (board-rows 0) (board-rows 1) (board-rows 2))))

(defn- check-columns [board]
  (let [board-columns (apply vector(partitioned-board board))]
  (map = (board-columns 0) (board-columns 1) (board-columns 2))))

(defn- get-left-diagonal [board]
  (loop [current-position 0 diagonal []]
    (if (= 3 (count diagonal))
      diagonal
      (if (= 0 (mod current-position 4))
        (recur (inc current-position) (conj diagonal (board current-position)))
        (recur (inc current-position) diagonal)))))

(defn- get-right-diagonal [board]
  (loop [current-position 2 diagonal []]
    (if (= 3 (count diagonal))
      diagonal
    (recur (+ 2 current-position) (conj diagonal (board current-position))))))

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

