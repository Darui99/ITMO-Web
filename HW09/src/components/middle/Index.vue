<template>
    <div>
        <Article v-for="post in posts" :post="post" :users="users" :comments="comments" :key="post.id"/>
    </div>
</template>

<script>
    import Article from "../Article";
    export default {
        name: "Index",
        props: ['users', 'posts', 'comments'],
        components: {Article}
    }
</script>

<style scoped>

</style>
