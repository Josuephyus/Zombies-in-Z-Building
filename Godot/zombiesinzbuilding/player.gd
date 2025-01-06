extends Node2D
signal hit

@export var speed = 100
var screen_size
var prev_facing = 2
var facing = 2


# Called when the node enters the scene tree for the first time.
func _ready():
	screen_size = get_viewport_rect().size
	hide()


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float):
	var velocity = Vector2.ZERO
	
	if (Input.is_action_pressed("move_R")):
		velocity.x += 1
	if (Input.is_action_pressed("move_L")):
		velocity.x -= 1
	if (Input.is_action_pressed("move_U")):
		velocity.y -= 1
	if (Input.is_action_pressed("move_D")):
		velocity.y += 1
	
	
	if (velocity.length() > 0):
		velocity = velocity.normalized() * speed
		
		if (velocity.x > 0):
			facing = 6
		if (velocity.x < 0):
			facing = 4
		if (velocity.y > 0):
			facing = 2
		if (velocity.y < 0):
			facing = 8
			
		if (prev_facing != facing):
			$AnimatedSprite2D.animation = "Walk_" + str(facing)
			prev_facing = facing
			
		$AnimatedSprite2D.play()
	else:
		$AnimatedSprite2D.animation = "Stand_" + str(facing)
		$AnimatedSprite2D.stop()
		
	position += velocity * delta
	
