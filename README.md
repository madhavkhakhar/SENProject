# SENProject
For creating a new level, follow these steps:

git pull origin master
git checkout -b "LevelName"

After completing the level,
git push origin "LevelName"

If there is a conflict, go to that file. Remove comments like <<<Stashed changes etc and push the changes by doing
git add .
git commit -m "Your commit message"
git push origin "LevelName"


