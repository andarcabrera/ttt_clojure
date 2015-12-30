(ns ttt.core-spec
  (:require [speclj.core :refer :all]
            [ttt.core :refer :all]))

(defn- make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(describe "main"
  (around [it]
    (with-out-str (it)))

  (it "returns a winning message"
    (should= nil
      (with-in-str (make-input '("Anda" "a" "Eli" "e" "1" "0" "3" "1" "4" "2"))
        (-main)))))

(describe "main"
  (around [it]
    (with-out-str (it)))

  (it "returns asks for user move until valid move is provided"
    (should= nil
      (with-in-str (make-input '("Anda" "a" "Eli" "e" "1" "0" "0" "3" "1" "1" "4" "2"))
        (-main)))))

(describe "main"
  (around [it]
    (with-out-str (it)))

  (it "return the message for a tie"
    (should= nil
      (with-in-str (make-input '("Anda" "a" "Eli" "e" "1" "0" "2" "1" "3" "5" "4" "6" "8" "7"))
        (-main)))))

