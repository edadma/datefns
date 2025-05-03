rm -r docs/*
touch docs/.nojekyll
cp -r target/scala-3.6.4/api/* docs
