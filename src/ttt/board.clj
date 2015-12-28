(ns ttt.board)

(defn surface [size]
  (vec (range size)))

(defn- transpose-board [board]
  (apply mapv vector board))

(defn- check-rows [board]
  (let [board-rows (transpose-board (partition 3 board))]
  (map = (board-rows 0) (board-rows 1) (board-rows 2))))

(defn- check-columns [board]
  (let [board-columns (apply vector(partition 3 board))]
  (map = (board-columns 0) (board-columns 1) (board-columns 2))))

(defn- solved-row? [board]
  (some true? (check-rows board)))

(defn- solved-column? [board]
  (some true? (check-columns board)))

(defn solved-board? [board]
  (if (or (solved-row? board) (solved-column? board)) true false))

