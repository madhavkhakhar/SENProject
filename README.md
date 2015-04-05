# SENProject
List of levels (done):
Level 1 - 1_1, 1_2
Level 2 - 2_1, 2_3
Level 3 - 3_1
Level 4- 4_1, 4_2
Level 5 - 5_1, 5_2
Level 6 - 6_1, 6_2
Level 7 - 7_1, 7_2
Level 8 - 8_1
Level 10 - 10_1
Level 11 - 11_1, 11_2, 11_3
Level 12 - 12_1
Level 14 - 14_2, 14_3
Level 15 - 15_2, 15_3
Level 16 - 16_1
Level 20 - 20_1, 20_2


For creating a new level, follow these steps:

git pull origin master
git checkout -b "LevelName"

After completing the level,
git push origin "LevelName"

If there is a conflict, go to that file. Remove comments like <<<Stashed changes etc and push the changes by doing,

git add .

git commit -m "Your commit message"

git push origin "LevelName"


