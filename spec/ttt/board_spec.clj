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

  (it "returns true if a row is solved"
    (def board ["X" "X" "X" "Y" "Y" 5 6 7 8 ])
    (should= true (solved-board? board)))

  (it "returns true if a column is solved"
    (def board ["X" "Y" "X" 3 "Y" 5 6 "Y" 8 ])
    (should= true (solved-board? board)))

  (it "returns true if the left diagonal is solved"
    (def board ["X" "Y" 2 3 "X" 5 6 "Y" "X" ])
    (should= true (solved-board? board)))

  (it "returns true if the right diagonal is solved"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= true (solved-board? board)))
  )

(describe "available-spot"
  (it "returns true if a spot is available"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= true (available-spot? board 3)))

  (it "returns false if a spot is not available"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= false (available-spot? board 0))))

(describe "fill-spot"
  (it "updates the spot with the given marker if spot is available"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= ["X" "X" "Y" 3 "Y" 5 "Y" "X" "X" ] (fill-spot board 1 "X")))

  (it "does not update the spot with the given marker if spot is not available"
    (def board ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ])
    (should= ["X" 1 "Y" 3 "Y" 5 "Y" "X" "X" ] (fill-spot board 4 "X"))) )



