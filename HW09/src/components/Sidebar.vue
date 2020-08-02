<template>
    <aside>
        <SidebarPost v-for="post in viewPosts" :post="post" :users="users" :key="post.id"/>
    </aside>
</template>

<script>
    import SidebarPost from "./SidebarPost";
    export default {
        name: "Sidebar",
        props: ['viewPosts', 'users'],
        components: {
            SidebarPost
        }
    }
</script>

<style scoped>

</style>