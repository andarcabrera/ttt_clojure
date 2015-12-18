(defproject ttt "0.1.0-SNAPSHOT"
  :description "Writing a TTT to further my learning of clojure"
  :url "not gonna happen"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-RC2"]
  [clj-http "2.0.0"]]
  :profiles {:dev {:dependencies [[speclj "3.3.1"]]}}
  :plugins [[speclj "3.3.1"]]
  :test-paths ["spec"]
  :main ttt.core)
