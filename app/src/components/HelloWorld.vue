<template>
  <div class="hello border-top-1px">
    <h1 class="fs26">{{ msg }}</h1>
    <div class="busd border-top-1px">
      <cube-button>Button</cube-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus">
h3
  margin: 40px 0 0
ul
  list-style-type: none
  padding: 0
li
  display: inline-block
  margin: 0 10px
a
  color: #42b983
.busd
  width: 200px
</style>
