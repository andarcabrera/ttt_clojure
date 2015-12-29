(ns ttt.player-spec
  (:require [speclj.core :refer :all]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.player :refer :all]))

(describe "player-name"
  (around [it]
    (with-redefs
      [ input/get-user-input #(str "Anda")
        output/prompt (constantly nil)]
      (it)))
  (it "contains the player name"
    (should= "Anda" ((create-player) :name))))

(describe "player-marker"
  (around [it]
    (with-redefs
      [ input/get-user-input (constantly "X")
        output/prompt (constantly nil)]
      (it)))
  (it "contains the player marker"
    (should= "X"
        ((create-player) :marker))))