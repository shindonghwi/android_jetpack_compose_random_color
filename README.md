# JetpackCompose_Modifier_RandomColor
JetpackCompose_Modifier_RandomColor

![ezgif com-gif-maker (3)](https://user-images.githubusercontent.com/45490440/161371085-96ad9d3d-c612-4e92-89b7-8722745206f0.gif)

### It's Easy!

```
Box(modifier = modifier.background(Color.random())){}

// 랜덤 칼라 가져오기
fun Color.Companion.random(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}
```
