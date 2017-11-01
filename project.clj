(defproject clojure-automata "0.1.0-SNAPSHOT"
  :description "Playing around with cellular automata"
  :url "http://github.com/cmazzullo/clojure-automata.git"
  :license {:name "GNU General Public License"
            :url "https://www.gnu.org/licenses/gpl.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot clojure-automata.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
