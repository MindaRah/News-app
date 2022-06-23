package com.example.mvvmretrofitapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mvvmretrofitapp.model.News
import com.example.mvvmretrofitapp.network.NewsRepositoryInterface
import com.example.mvvmretrofitapp.utils.ResponseStatus
import com.example.mvvmretrofitapp.viewModel.NewsViewModel
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {
    @get:Rule
    val instantTaskExecutorTest: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var getAllNews: NewsRepositoryInterface
    private val resultData = getResultData()

    @Mock
    lateinit var observer: Observer<ResponseStatus>

    private lateinit var viewModel: NewsViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var lifeCycle: LifeCycleTest

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = NewsViewModel(getAllNews, testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `make sure the result is not null`() =
        testCoroutineRule.runBlockingTest {
            lifeCycle.onResume()

            Mockito.`when`(getAllNews.getAllNews()).thenReturn(null)

            viewModel.subscribeToNews()

            Mockito.verify(getAllNews, Mockito.times(2)).getAllNews()
            Mockito.verify(observer).onChanged(ResponseStatus.LOADING())
        }

    fun getResultData(): News {
        val RESULT_STRING = "{\"category\": \"science\", \"data\": [{\"author\": \"Medhaa Gupta\", \"content\": \"A memorandum of understanding (MoU) has been signed between Ladakh's administration, LAHDC Leh and Indian Institute of Astrophysics (IIA) to set up India's first Dark Sky Reserve at Hanle's Changthang Wildlife Sanctuary. \\\"[It'll] preserve the pristine dark skies of Hanle for science,\\\" IIA tweeted. Moreover, Ladakh Lieutenant Governor said that the site will contribute to the economy of the locals.\", \"date\": \"17 Jun 2022,Friday\", \"id\": \"83c9190f9c794f2b80f18912993324af\", \"imageUrl\": \"https://static.inshorts.com/inshorts/images/v1/variants/jpg/m/2022/06_jun/17_fri/img_1655448420052_464.jpg?\", \"readMoreUrl\": null, \"time\": \"01:30 pm\", \"title\": \"MoU signed to set up India's first Dark Sky Reserve in Ladakh\", \"url\": \"https://www.inshorts.com/en/news/mou-signed-to-set-up-indias-first-dark-sky-reserve-in-ladakh-1655452849256\"}, {\"author\": \"Swati Dubey\", \"content\": \"Scientists have developed a screening process that combines information about pattern of retina's blood vessels with genetic data to predict the risk of coronary artery disease (CAD) and its potentially fatal outcome, myocardial infarction, commonly known as a heart attack in an individual. Scientists say processes could also be developed to predict diseases like diabetic retinopathy and stroke.\\n\", \"date\": \"14 Jun 2022,Tuesday\", \"id\": \"646ea9aa7d2e48209da4b22c8bdb7efc\", \"imageUrl\": \"https://static.inshorts.com/inshorts/images/v1/variants/jpg/m/2022/06_jun/14_tue/img_1655192642269_610.jpg?\", \"readMoreUrl\": \"https://www.independent.co.uk/news/science/eye-test-heart-attack-risk-b2099687.html?utm_campaign=fullarticle&utm_medium=referral&utm_source=inshorts \", \"time\": \"02:03 pm\", \"title\": \"Scientists develop screening process to predict heart attack risk from eye test\", \"url\": \"https://www.inshorts.com/en/news/scientists-develop-screening-process-to-predict-heart-attack-risk-from-eye-test-1655195636064\"}], \"success\": true}"
        val gson = Gson()
        return gson.fromJson(RESULT_STRING, News::class.java)
    }

}