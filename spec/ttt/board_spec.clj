(ns ttt.board-spec
  (:require [speclj.core :refer :all]
            [ttt.board :refer :all]))

(describe "surface"
  (it "has 9 spots if the size is 9"
    (should= 9 (count (surface 9))))

  (it "has 16 spots if the size is 16"
    (should= 16 (count (surface 16)))))

(describe "solved-board?"
 (it "returns false if there is no winner"
  (def board ["X" "Y" "X" "Y" "X" 5 6 7 8 ])
  (should= false (solved-board? board)))

  (it "returns true if a row is solved on a 3X3 board"
    (def board ["X" "X" "X" "Y" "Y" 5 6 7 8 ])
    (should= true (solved-board? board)))

  (it "returns true if a row is solved on a 4X4 board"
    (def board [0 1 2 3 4 "Y" "Y" "Y" 8 9 10 11 "X" "X" "X" "X"])
    (should= true (solved-board? board)))

  (it "returns false if a row is not solved on a 4X4 board"
    (def board [0 1 2 3 4 "Y" "Y" "Y" 8 9 10 11 "X" "X" "X" "Y"])
    (should= false (solved-board? board)))

  (it "returns true if a column is solved on 3X3 board"
    (def board ["X" "Y" "X" 3 "Y" 5 6 "Y" 8 ])
    (should= true (solved-board? board)))

  (it "returns true if a column is solved on 4X4 board"
    (def board ["X" 1 2 3 "X" "Y" "Y" "Y" "X" 9 10 11 "X" "Y" "Y" "Y"])
    (should= true (solved-board? board)))

  (it "returns false if a column is not solved on 4X4 board"
    (def board [0 "X" 2 3 "Y" "X" 6 7 8 "X" 10 11 "Y" 13 14 15])
    (should= false (solved-board? board)))

  (it "returns true if the left diagonal is solved"
    (def board ["X" "Y" 2 3 "X" 5 6 "Y" "X" ])
    (should= true (solved-board? board)))

  (it "returns true if the left diagonal is solved on a 4X4 board"
    (def board ["X" 1 2 3 4 "X" 6 7 8 9 "X" 11 "Y" 13 14 "X"])
    (should= true (solved-board? board)))

  (it "returns false if the left diagonal is not solved on a 4X4 board"
    (def board ["X" 1 2 3 4 "X" 6 7 8 9 "X" 11 "Y" 13 14 "Y"])
    (should= false (solved-board? board)))

  (it "returns true if the right diagonal is solved"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= true (solved-board? board)))

  (it "returns true if the right diagonal is solved on a 4X4 board"
    (def board [0 1 2 "Y" 4 "X" "Y" 7 8 "Y" "X" 11 "Y" 13 14 "Y"])
    (should= true (solved-board? board)))
  )

(describe "available-spot"
  (it "returns true if a spot is available"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= true (available-spot? board 3)))

  (it "returns false if a spot is not available"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= false (available-spot? board 0))))

(describe "all-available-spots"
  (it "returns all available spots on the board"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= [1 3 5] (all-available-spots board))))

(describe "fill-spot"
  (it "updates the spot with the given marker if spot is available"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= ["X" "X" "Y" 3 "Y" 5 "Y" "X" "X" ] (fill-spot board 1 "X")))

  (it "does not update the spot with the given marker if spot is not available"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ] (fill-spot board 4 "X"))) )

(describe "tied-board"
  (it "returns true if the board is tied"
    (def board ["X" "Y" "X" "X" "Y" "X" "Y" "X" "Y" ])
    (should= true (tied-board? board)))

  (it "returns true if the board is tied for a 4X4 board"
    (def board ["X" "Y" "Y" "X" "X" "Y" "Y" "Y" "X" "Y" "X" "Y" "Y" "X" "Y" "Y"])
    (should= true (tied-board? board)))

  (it "return false if the board is not tied"
    (def board ["X" "Y" "X" "Y" 4 5 6 7 8 ])
    (should= false (tied-board? board)))

  (it "return false if the board is solved"
    (def board ["X" "X" "Y" "X" "Y" "Y" "Y" "X" "Y" ])
    (should= false (tied-board? board))))



