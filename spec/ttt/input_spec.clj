(ns ttt.input-spec
  (:require [speclj.core :refer :all]
            [ttt.input :refer :all]))

(describe "get-user-input"
  (it "collects input from the user"
    (should= "Hello"
      (with-in-str "Hello"
        (get-user-input)))))