package com.example.olympiadsemifinal.domain.usecase

import com.example.olympiadsemifinal.domain.ServiceRepository
import com.example.olympiadsemifinal.domain.model.Service
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetServiceListUseCaseTest {
    class TestRepository : ServiceRepository {
        override suspend fun getServiceList(): List<Service> {
            return TEST_LIST

        }
    }

    @Test
    fun `should return the same data as in repository`() {
        val testRepository = TestRepository()
        val useCase = GetServiceListUseCase(testRepository)
        val actual = runBlocking { useCase.invoke() }
        val excepted = TEST_LIST

        assertEquals(actual, excepted)


    }

    companion object {
        val TEST_LIST = listOf(
            Service(
                "ВКонтакте",
                "Самая популярная соцсеть и первое суперприложение в Роcсии",
                "https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-vk.png",
                "https://vk.com/"
            ),
            Service(
                "Одноклассники",
                "Первая соцсеть в России, развлекательная платформа с играми, видео и музыкой",
                "https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-ok.png",
                "https://ok.ru/"
            ),
        )
    }
}

