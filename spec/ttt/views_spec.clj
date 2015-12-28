(ns ttt.views-spec
  (:require [speclj.core :refer :all]
            [ttt.views :refer :all]))

(describe "welcome-message"
  (it "displays a welcome-message"
    (should= "Welcome to my TTT!" welcome-message)))