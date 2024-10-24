package dev.sanjaygangwar.tempproject.ui.fragment.home

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.sanjaygangwar.tempproject.databinding.HomeBinding
import dev.sanjaygangwar.tempproject.models.dataclass.Items
import dev.sanjaygangwar.tempproject.utils.baseclasses.BaseFragment
import dev.sanjaygangwar.tempproject.utils.mUtils.mLog

@AndroidEntryPoint
class Home : BaseFragment<HomeBinding>(HomeBinding::inflate), HomeAdapter.ItemListener, HomeAdapter.LongItemListener {

    private val viewModel: HomeViewModel by viewModels()
    private var listOfApp = ArrayList<Items>()
    private var adapter: HomeAdapter? = null
    override fun getDataFromTheServer() {}
    override fun initAllComponents() {
        listOfApp.clear()
        listOfApp.add(
            Items(
                name = "Mails",
                isApp = true,
                link = "com.microsoft.office.outlook",
                imageUrl = "https://cdn.iconscout.com/icon/free/png-256/free-google-mail-new-logo-icon-download-in-svg-png-gif-file-formats--gmail-one-ui-pack-logos-icons-3955524.png"
            )
        )
        listOfApp.add(
            Items(
                name = "Google",
                isApp = false,
                link = "https://www.google.com/about/careers/applications/dashboard",
                imageUrl = "https://cdn1.iconfinder.com/data/icons/google-s-logo/150/Google_Icons-09-512.png"
            )
        )
        listOfApp.add(
            Items(
                name = "HackAJob",
                isApp = false,
                link = "https://user.hackajob.com/dashboard",
                imageUrl = "https://media.licdn.com/dms/image/v2/D560BAQF6HYG6dvSK0Q/company-logo_200_200/company-logo_200_200/0/1683088716419/hackajob_logo?e=2147483647&v=beta&t=xCEpmerToqnMH2syL-pKbrceBtCIvKKwNHcXP5Cnzc8"
            )
        )
        listOfApp.add(Items(name = "Linkedin", isApp = true, link = "com.linkedin.android", imageUrl = "https://cdn-icons-png.flaticon.com/512/174/174857.png"))
        listOfApp.add(
            Items(
                name = "Hirect",
                isApp = true,
                link = "in.hirect",
                imageUrl = "https://play-lh.googleusercontent.com/mkuNjtUPpNAvk0tI6NcllPqRO2jnGS3W5TJIHJGlCrOUVcaM5ZzVyFBkmUjL4H3Tdg"
            )
        )
        listOfApp.add(
            Items(
                name = "Naukri",
                isApp = true,
                link = "naukriApp.appModules.login",
                imageUrl = "https://play-lh.googleusercontent.com/76gEFhQto5xMHr2Qf8nWLvm1s0O60clhkwHvxQDSeI3hthf7Zs05JJQeyg5H347DGQ"
            )
        )
        listOfApp.add(
            Items(
                name = "Instahire",
                isApp = false,
                link = "https://www.instahyre.com/candidate/opportunities/?matching=true",
                imageUrl = "https://static.instahyre.com/images/logos/logo.webp"
            )
        )
        listOfApp.add(
            Items(
                name = "GlassDoor",
                isApp = true,
                link = "com.glassdoor.app",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiGXOdi2l8mOB1H7mZVSrh5HUuo_NqjNesWw&s"
            )
        )
        listOfApp.add(Items(name = "Indeed", isApp = true, link = "com.indeed.android.jobsearch", imageUrl = "https://pageflows.com/media/logos/indeed.jpg"))
        listOfApp.add(
            Items(
                name = "WeekDay",
                isApp = false,
                link = "https://jobs.weekday.works/",
                imageUrl = "https://jobs.weekday.works/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Flogo.268caeb2.png&w=384&q=75"
            )
        )
        listOfApp.add(Items(name = "Hirist", isApp = true, link = "com.hirist.jobseeker", imageUrl = "https://staticlogo.hirist.com/webp/hirist-tech-logo.webp"))
        listOfApp.add(Items(name = "BigShift", isApp = true, link = "com.infoedge.bigshyft", imageUrl = "https://www.bigshyft.com/static/mailers/Logo.png"))
        listOfApp.add(
            Items(
                name = "StartUp Jobs",
                isApp = false,
                link = "https://startup.jobs/",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBi8NHnzhXiB3Fp6qSTKR_UBFWn5kS9z4FXw&s"
            )
        )
        listOfApp.add(
            Items(
                name = "WellFounded",
                isApp = false,
                link = "https://angel.co/",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaNns9mjPx9NgfrCX2yubncghgP_urft_6OQ&s"
            )
        )
        listOfApp.add(
            Items(
                name = "TopHire",
                isApp = false,
                link = "https://tophire.co/",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa7NgQAy5myFT5oId-0Al68hHxmIm1xHp7bg&s"
            )
        )
        listOfApp.add(
            Items(
                name = "Turing",
                isApp = false,
                link = "https://developers.turing.com/",
                imageUrl = "https://pbs.twimg.com/profile_images/1807814297461907464/xENEAURW_400x400.png"
            )
        )
        listOfApp.add(
            Items(
                name = "LoopCV",
                isApp = false,
                link = "https://app.loopcv.pro/",
                imageUrl = "https://www.loopcv.pro/assets/images/logos/loopcv-new-logo-navbar.png"
            )
        )
        listOfApp.add(
            Items(
                name = "ChatGPT",
                isApp = true,
                link = "com.openai.chatgpt",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRt_I0AqTbvaxxFCoEWH_SZP49jpBiTMiCkCA&s"
            )
        )


        adapter = HomeAdapter(requireContext(), this, this)
        bind?.itemListRecycler?.adapter = adapter
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) { 6 } else { 3 }
        bind?.itemListRecycler?.layoutManager = GridLayoutManager(context, spanCount)
        adapter?.setItems(listOfApp)
    }
    override fun initAllObserver() {}
    override fun initOnClickListener() {}

    override fun onViewClicker(p0: View?) {
        when (p0?.id) {

        }
    }

    override fun onViewLongClicker(p0: View?) {
    }

    override fun onClicked(itemData: Items) {

        handleClickForItems(context, itemData)
    }

    private fun handleClickForItems(context: Context?, itemData: Items) {
        if (itemData.isApp) {
            openAppByPackageName(context, itemData.link)
        } else {
            val action = HomeDirections.actionHome2ToSetting(itemData.link)
            action.navigate()
        }
    }

    private fun openAppByPackageName(context: Context?, packageName: String) {
        mLog(packageName)
        var launchIntent: Intent? = null
        try {
            launchIntent = context?.packageManager?.getLaunchIntentForPackage(packageName)
        } catch (ignored: Exception) {
        }

        if (launchIntent == null) {
            startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
        } else {
            startActivity(launchIntent)
        }
    }

    override fun onLongClicked(itemData: Items) {
        handleClickForItems(context, itemData)
    }


}