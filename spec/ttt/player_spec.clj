(ns ttt.player-spec
  (:require [speclj.core :refer :all]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.player :refer :all]))

(defn- make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(describe "player-name"
  (around [it]
    (with-redefs
      [ input/get-user-input #(str "Anda")
        output/prompt (constantly nil)]
      (it)))
  (it "contains the player name"
    (should= "Anda" ((create-player) :name))))

(describe "player-name"
  (around [it]
    (with-out-str (it)))

  (it "if no input it requests input until something is entered"
    (should= "Anda"
      (with-in-str (make-input '("" "" "Anda"))
        ((create-player) :name)))))

(describe "player-marker"
  (around [it]
    (with-redefs
      [ input/get-user-input (constantly "X")
        output/prompt (constantly nil)]
      (it)))
  (it "contains the player marker"
    (should= "X"
        ((create-player) :marker))))

(describe "select-spot"
  (around [it]
    (with-out-str (it)))

  (it "asks for player spot choice"
    (def board ["X" "Y" "X" 3 "X" 5 6 7 8 ])
    (should= 3
      (with-in-str "3"
        (select-spot board)))))

(describe "select-spot"
  (around [it]
    (with-out-str (it)))

  (it "asks for player spot choice repeatedly until they select valid spot"
    (def board ["X" "Y" "X" "Y" "X" 5 6 7 8 ])
    (should= 5
      (with-in-str (make-input '("1" "5"))
        (select-spot board)))))

(describe "select-spot"
  (around [it]
    (with-out-str (it)))

  (it "gives an invalid input error if spot no selection is made"
    (def board ["X" "Y" "X" "Y" "X" 5 6 7 8 ])
    (should= 5
      (with-in-str (make-input '("" "" "5"))
        (select-spot board)))))