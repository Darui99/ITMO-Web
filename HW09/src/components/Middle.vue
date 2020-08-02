<template>
    <div class="middle">
        <Sidebar :viewPosts="viewPosts" :users="users"/>
        <main>
            <Index v-if="page === 'Index'" :users="users" :posts="sortedPosts" :comments="comments"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <AddPost v-if="page === 'AddPost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Users v-if="page === 'Users'" :users="users"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import AddPost from './middle/AddPost';
    import Sidebar from './Sidebar';
    import EditPost from "./middle/EditPost";
    import Users from "./middle/Users";

    export default {
        name: "Middle",
        props: ['users', 'posts', 'comments'],
        data: function () {
            return {
                page: "Index"
            }
        },
        computed: {
            sortedPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id);
            },
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
            }
        },
        components: {
            EditPost,
            Index,
            Enter,
            Register,
            Sidebar,
            AddPost,
            Users
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page) => {
                this.page = page;
            });
        }
    }
</script>

<style scoped>

</style>
