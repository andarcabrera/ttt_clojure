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
    (should= "Anda" ((create-human-player) :name))))

(describe "player-name"
  (around [it]
    (with-out-str (it)))

  (it "if no input it requests input until something is entered"
    (should= "Anda"
      (with-in-str (make-input '("" "" "Anda"))
        ((create-human-player) :name)))))

(describe "player-marker"
  (around [it]
    (with-redefs
      [ input/get-user-input (constantly "X")
        output/prompt (constantly nil)]
      (it)))
  (it "contains the player marker"
    (should= "X"
        ((create-human-player) :marker))))

(describe "player-marker"
  (around [it]
    (with-redefs
      [ input/get-user-input (constantly "X")
        output/prompt (constantly nil)]
      (it)))
  (it "should be of type human"
    (should= "human"
        ((create-human-player) :type))))

(describe "select-spot"
  (around [it]
    (with-out-str (it)))

  (it "asks for player spot choice"
    (def info {:board ["X" "Y" "X" 3 "X" 5 6 7 8 ] :markers ["X" "Y"] :type "human"})
    (should= 3
      (with-in-str "3"
        (select-spot info)))))

(describe "select-spot"
  (around [it]
    (with-out-str (it)))

  (it "asks for player spot choice repeatedly until they select valid spot"
    (def info {:board ["X" "Y" "X" "Y" "X" 5 6 7 8 ] :markers ["X" "Y"] :player-marker "X" :type "human"})
    (should= 5
      (with-in-str (make-input '("1" "5"))
        (select-spot info)))))

(describe "select-spot"
  (around [it]
    (with-out-str (it)))

  (it "gives an invalid input error if spot no selection is made"
    (def info {:board ["X" "Y" "X" "Y" "X" 5 6 7 8 ] :markers ["X" "Y"] :player-marker "X" :type "human"})
    (should= 5
      (with-in-str (make-input '("" "" "5"))
        (select-spot info)))))

(describe "select-spot-computer"
  (around [it]
    (with-out-str (it)))

  (it "computer selects spot 4 if all spots available"
    (def info {:board [0 1 2 3 4 5 6 7 8 ] :markers ["X" "Y"] :player-marker "X" :type "computer"})
    (should= 4
        (select-spot info))))

(describe "select-spot-computer"
  (around [it]
    (with-out-str (it)))

  (it "selects winning spot if available"
    (def info {:board ["X" 1 "X" 3 "X" 5 "Y" "Y" 8 ] :markers ["X" "Y"] :player-marker "Y" :type "computer"})
    (should= 8
        (select-spot info))))

(describe "select-spot-computer"
  (around [it]
    (with-out-str (it)))

  (it "prevents opponent from winning"
    (def info {:board ["X" "X" 2 3 "Y" 5 6 7 8 ] :markers ["X" "Y"] :player-marker "Y" :type "computer"})
    (should= 2
        (select-spot info))))

(describe "computer-player"
  (around [it]
    (with-redefs
      [ input/get-user-input (constantly "Y")
        output/prompt (constantly nil)]
      (it)))
  (it "is of type computer"
    (should= "computer"
        ((create-computer-player) :type)))

  (it "has the generic name computer"
    (should= "computer"
        ((create-computer-player) :name)))

  (it "it has a Y marker"
    (should= "Y"
        ((create-computer-player) :marker))))