# Skatteetaten Tick Loop Function
# This runs itself repeatedly until timer finishes

# === DECREMENT TIMER ===
execute as @s store result score #current_timer tempscore run data get entity @s skatteetatenSpawnTimer 0
execute as @s if score #current_timer tempscore matches 1.. run data modify entity @s skatteetatenSpawnTimer set value -1

# === CHECK IF TIMER FINISHED ===
execute as @s store result score #current_timer tempscore run data get entity @s skatteetatenSpawnTimer 0
execute as @s if score #current_timer tempscore matches 0 run tellraw @s ["",{"text":"[DEBUG] ","color":"gold"},{"text":"TIMER FINISHED! Spawning Skatteetaten!","color":"red"}]

# === SPAWN SKATTEETATEN WHEN TIMER REACHES 0 ===
execute as @s at @s if score #current_timer tempscore matches 0 run summon your_mod:skatteetaten ~2 ~ ~
execute as @s at @s if score #current_timer tempscore matches 0 run summon your_mod:skatteetaten ~-2 ~ ~
execute as @s at @s if score #current_timer tempscore matches 0 run summon your_mod:skatteetaten ~ ~ ~2
execute as @s at @s if score #current_timer tempscore matches 0 run summon your_mod:skatteetaten ~ ~ ~-2
execute as @s if score #current_timer tempscore matches 0 run tellraw @s ["",{"text":"SKATTEETATEN: You have been selected for a tax audit! Pay within 120 seconds!","color":"red"}]

# === RESET TIMER AFTER SPAWNING ===
execute as @s if score #current_timer tempscore matches 0 run data modify entity @s skatteetatenSpawnTimer set value 12000
execute as @s if score #current_timer tempscore matches 0 run tellraw @s ["",{"text":"[DEBUG] ","color":"gold"},{"text":"Timer reset to 12000","color":"green"}]

# === CONTINUE THE LOOP (wait 1 tick then run again) ===
execute as @s if score #current_timer tempscore matches ..12000 run schedule function your_mod:skatteetaten_tick_loop 1t