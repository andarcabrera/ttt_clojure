(ns ttt.board-spec
  (:require [speclj.core :refer :all]
            [ttt.board :refer :all]))

(describe "surface"
  (it "has 9 spots if the size is 9"
    (should= 9 (count (surface 9))))

  (it "has 16 spots if the size is 16"
    (should= 16 (count (surface 16)))))

(describe "solved-board?"
  (it "returns true if there is a winner"
    (def board1 ["X" "X" "X" "Y" "Y" 5 6 7 8 ])
    (def markers1 ["X" "Y"])
    (should= true (solved-board? board1 markers1)))

  (xit "returns false if there is no winner"
    (def board2 ["X" "Y" "X" "Y" "X" 5 6 7 8 ])
    (def markers2 ["X" "Y"])
    (should= false (solved-board? board2 markers2))))



