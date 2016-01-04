(ns ttt.setup-spec
  (:require [speclj.core :refer :all]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.setup :refer :all]))

(defn- make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(describe "board-size"
   (around [it]
    (with-redefs
      [ input/get-user-input #(str "2")
        output/prompt (constantly nil)]
      (it)))
  (it "asks for board-size"
    (should= 16
        (board-size))))

(describe "board-size"
  (around [it]
    (with-out-str (it)))

  (it "prompts user to re-enter selection if the selection is invalid until a valid selection is made"
    (should= 9
      (with-in-str (make-input '("nine" "1"))
        (board-size)))))


(describe "validate-players"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user to enter the player information"
    (should= [{:type "human" :name "Anda", :marker "X"} {:type "human" :name "Eli", :marker "Y"}]
      (with-in-str (make-input '("1" "Anda" "X" "Eli" "Y"))
        (validate-players)))))

(describe "validate-players"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user to enter the player information until valid"
    (should= [{:type "human" :name "Anda", :marker "X"} {:type "human" :name "Eli", :marker "Y"}]
      (with-in-str (make-input '("1" "Anda" "X" "Eli" "X" "Y"))
        (validate-players)))))

(describe "validate-players"
  (around [it]
    (with-out-str (it)))

  (it "creates one computer and one human player"
    (should= [{:type "human" :name "Anda", :marker "X"} {:type "computer" :name "computer", :marker "Y"}]
      (with-in-str (make-input '("2" "Anda" "X" "Y"))
        (validate-players)))))

(describe "game-type"
  (around [it]
    (with-out-str (it)))

  (it "asks for player spot choice"
    (should= 3
      (with-in-str "3"
        (game-type)))))

(describe "game-type"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user for game-type until a valid game-type is entered"
    (should= 2
      (with-in-str (make-input '("0" "9" "a" "2"))
        (game-type)))))



