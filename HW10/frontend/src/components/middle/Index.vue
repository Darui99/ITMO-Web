<template>
    <div>
        <Article v-for="post in posts" :post="post" :users="users" :key="post.id"/>
    </div>
</template>

<script>
    import Article from "../Article";
    export default {
        name: "Index",
        props: ['users', 'posts'],
        components: {Article}
    }
</script>

<style scoped>

</style>