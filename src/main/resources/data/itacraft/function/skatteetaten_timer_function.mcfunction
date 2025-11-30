# Skatteetaten Timer Function - SCOREBOARD VERSION
execute as @s run tellraw @s ["",{"text":"[DEBUG] Function started","color":"green"}]

# Check if timer already running
execute as @s store result score #temp_check tempscore run scoreboard players get @s skatte_timer

# If no timer, start it (value 0 means no timer)
execute as @s if score #temp_check tempscore matches 0 run scoreboard players set @s skatte_timer 12000
execute as @s if score #temp_check tempscore matches 0 run tellraw @s ["",{"text":"Timer started: 12000 ticks","color":"green"}]

# If timer exists, show current value  
execute as @s unless score #temp_check tempscore matches 0 run tellraw @s ["",{"text":"Timer running: ","color":"yellow"},{"score":{"name":"@s","objective":"skatte_timer"},"color":"red"},{"text":" ticks","color":"yellow"}]

# Show current scoreboard value
execute as @s run tellraw @s ["",{"text":"Current timer: ","color":"gold"},{"score":{"name":"@s","objective":"skatte_timer"},"color":"green"}]