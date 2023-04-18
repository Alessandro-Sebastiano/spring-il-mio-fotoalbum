<template>
    <div class="col-12 col-sm-6 col-lg-3" v-if="photo.visible">
        <div class="card p-0">
            <img :src="photo.url" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title mb-3 text-capitalize">{{ photo.title }}</h5>
                <router-link :to="{ name: 'show', params: { id: photo.id } }">
                    <button class="btn btn-primary">Show details</button>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CardComponent',

    props: ['photo'],

    data() {
        return {

        }
    }

}
</script>

<style lang="scss" scoped></style>