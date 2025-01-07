extends Node2D
signal hit

@export var speed = 100
var screen_size
var prev_facing = 2
var facing = 2


# Called when the node enters the scene tree for the first time.
func _ready():
	screen_size = get_viewport_rect().size
	$Sprite2D.flip_h = 1


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float):
	PlayerMove(delta)
	AimGun()
	
func PlayerMove(delta: float):
	
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

func AimGun():
	$Sprite2D.position = $AnimatedSprite2D.position
	$Sprite2D.look_at(get_global_mouse_position())
	var rot = $Sprite2D.rotation_degrees
	rot += PI
	rot = fmod(rot, 360)

	$Sprite2D.rotation_degrees = rot
	if (rot < 0):
		rot += 360
	
	if (rot < 90 or rot > 270):
		$Sprite2D.flip_v = 0
	else:
		$Sprite2D.flip_v = 1
		
	var rot_rad = deg_to_rad(rot)
	var distance = 125
	var vec_rot = Vector2(cos(rot_rad), sin(rot_rad))
	vec_rot = vec_rot * distance
	
	var player_pos = $AnimatedSprite2D.position
	$Sprite2D.position = player_pos + vec_rot
