(ns ttt.board)

(defn surface [size]
  (vec (range size)))

(defn transpose-board [board]
  (apply mapv vector board))

(defn check-row [board]
  (let [board-rows (transpose-board (partition 3 board))]
  (map = (board-rows 0) (board-rows 1) (board-rows 2))))

(defn solved-row? [board]
  (some true? (check-row board)))

(defn solved-board? [board]
  (if (solved-row? board) true false))

