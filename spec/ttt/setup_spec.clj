(ns ttt.setup-spec
  (:require [speclj.core :refer :all]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.setup :refer :all]))

(describe "board-size"
   (around [it]
    (with-redefs
      [ input/get-user-input (constantly 1)
        output/prompt (constantly nil)]
      (it)))
  (it "contains the player marker"
    (should= 1
        (board-size))))

(describe "board-size"
   (around [it]
    (with-redefs
      [ input/get-user-input (constantly 0)
        output/prompt (constantly nil)]
      (it)))
  (it "prompts user to re-enter selection if the selection is invalid"
    (should= (invalid-board-size)
        (board-size))))

