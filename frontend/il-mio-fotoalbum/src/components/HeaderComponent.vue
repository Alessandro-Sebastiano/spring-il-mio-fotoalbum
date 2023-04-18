<template>
    <div>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Photo Album</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <router-link :to="{ name: 'home' }">
                                <a class="nav-link" :class="this.$route.path == '/' ? 'active' : ''"
                                    aria-current="page">Home</a>
                            </router-link>
                        </li>
                        <li class="nav-item">
                            <router-link :to="{ name: 'home' }">
                                <a class="nav-link" :class="this.$route.path == '/contact' ? 'active' : ''">Contact</a>
                            </router-link>
                        </li>
                    </ul>
                    <span class="d-flex" role="search" v-if="this.$route.path == '/'">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
                            v-model="store.title">
                        <button class="btn btn-success me-2" @click="$emit('call')">Search</button>
                        <button class="btn btn-danger" @click="$emit('reset')">Reset</button>
                    </span>
                </div>
            </div>
        </nav>
    </div>
</template>

<script>
import { store } from '../store';
export default {
    name: "HeaderComponent",

    data() {
        return {
            store,
        }
    }

}
</script>

<style lang="scss" scoped></style>