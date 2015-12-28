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
  (def board2 ["X" "Y" "X" "Y" "X" 5 6 7 8 ])
  (should= false (solved-board? board2)))

  (it "returns true if a row is solved"
    (def board1 ["X" "X" "X" "Y" "Y" 5 6 7 8 ])
    (should= true (solved-board? board1))))



