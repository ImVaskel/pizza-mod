# Pizza Mod

## About
Just a mod that adds pizza related items to the game, this was mostly to let me learn modding and more kotlin.

### Notes
Just a couple notes on my decision decisions in the mods.
<details>
<summary>Why milk is so janky.</summary>
This probably doesn't need to be explained, but I'll do it anyways.
Unlike forge, Fabric does not register a milk fluid. This causes me to have to make my own.
Of course, I cannot just bind a milk fluid to the original milk bucket, that'd be too easy.
I have to make my own milk bucket with its own recipe and behaviors.
I don't want to do this, but I wanted to make the milk cauldron a fluid storage block.
</details>